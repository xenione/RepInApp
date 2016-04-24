package apps.xenione.com.repoinapp.example.infrastructure;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.concurrent.Callable;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class LoaderUseCase<T> extends AsyncTaskLoader<T> {

    private Callable<T> callable;
    private T result;

    public LoaderUseCase(Context context, Callable<T> callable) {
        super(context);
        this.callable = callable;
    }

    @Override
    public T loadInBackground() {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deliverResult(T result) {
        if (isReset()) {
            if (result != null) {
                onReleaseResources(result);
            }
        }
        T oldResut = this.result;
        this.result = result;

        if (isStarted()) {
            super.deliverResult(result);
        }

        if (oldResut != null) {
            onReleaseResources(oldResut);
        }
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }

        if (takeContentChanged() || result == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(T result) {
        super.onCanceled(result);
        onReleaseResources(result);
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        if (result != null) {
            onReleaseResources(result);
            result = null;
        }
    }

    protected void onReleaseResources(T result) {
    }
}
