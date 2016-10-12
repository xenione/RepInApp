package com.xenione.repoinapp.infrastructure.presenters;

/**
 * Created by Eugeni on 03/10/2016.
 */
public class BasePresenter<V> {

    protected V mView;

    public void bind(V view) {
        mView = view;
    }

    public void unbind() {
        mView = null;
    }
}
