package com.brian.floersch.uim;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.brian.floersch.uim.AssemblerV1.RootAssembler;
import com.brian.floersch.uim.AssemblerV1.UimContext;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Assembles a new UI from json
 */
public class UiAssembler {

    private static final String VERSION = "Version";

    private JSONObject mJsonObject;
    private ViewGroup mParent;
    private RootAssembler mParser;
    private IuimEvents mEventHandler;
    private Context mContext;

    /**
     * Creates a new UiAssembler class instance
     *
     * @param jsonObject   the json object
     * @param eventHandler the event handler
     * @param context      the application context
     * @param parent       the parent view
     */
    public UiAssembler(JSONObject jsonObject, IuimEvents eventHandler, Context context, ViewGroup parent) {
        mJsonObject = jsonObject;
        mEventHandler = eventHandler;
        mContext = context;
        mParent = parent;
    }

    /**
     * Creates a new UiAssembler class instance
     *
     * @param json         the json string
     * @param eventHandler the event handler
     * @param context      the application context
     * @param parent       the parent view
     * @throws JSONException
     */
    public UiAssembler(String json, IuimEvents eventHandler, Context context, ViewGroup parent) throws JSONException {
        mJsonObject = new JSONObject(json);
        mEventHandler = eventHandler;
        mContext = context;
        mParent = parent;
    }

    /**
     * returns the new generated view (null until parseAndApplyView is called)
     *
     * @return the view
     */
    public ViewGroup getView() {
        return mParser.getView();
    }

    /**
     * Parses json and generates the new view structure
     *
     * @throws JSONException
     */
    public void parseAndApplyView() throws JSONException {

        int version = mJsonObject.getInt(VERSION);

        switch (version) {
            case 1:
                mParser = new RootAssembler(mJsonObject, new UimContext(mEventHandler, mContext, mParent), mParent);
                mParser.parse();
                break;
        }
    }

    /**
     * gets a view from the string ID provided in json
     *
     * @param id the string id provided in json
     * @return the view if found
     */
    public View getViewByStringId(String id) {
        return mParent.findViewById(id.hashCode());
    }
}
