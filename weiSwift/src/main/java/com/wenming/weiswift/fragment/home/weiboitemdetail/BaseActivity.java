package com.wenming.weiswift.fragment.home.weiboitemdetail;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.openapi.CommentsAPI;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.wenming.weiswift.R;
import com.wenming.weiswift.common.login.AccessTokenKeeper;
import com.wenming.weiswift.common.login.Constants;

/**
 * Created by wenmingvs on 16/4/20.
 */
public abstract class BaseActivity extends Activity {

    public Context mContext;
    public AuthInfo mAuthInfo;
    public Oauth2AccessToken mAccessToken;
    public StatusesAPI mStatusesAPI;
    public CommentsAPI mCommentsAPI;
    public SsoHandler mSsoHandler;
    public Status mWeiboItem;

    public View mToolBar;
    public ImageView mBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        mContext = this;
        mWeiboItem = getIntent().getParcelableExtra("weiboitem");
        setContntView();
        initTitleBar();
        initAccessToken();
    }


    public abstract void setContntView();

    private void initAccessToken() {
        mAuthInfo = new AuthInfo(mContext, Constants.APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(BaseActivity.this, mAuthInfo);
        mAccessToken = AccessTokenKeeper.readAccessToken(mContext);
        mCommentsAPI = new CommentsAPI(mContext, Constants.APP_KEY, mAccessToken);
    }

    private void initTitleBar() {
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.toolbar_home_weiboitem_detail_title);
        mToolBar = findViewById(R.id.toolbar_home_weiboitem_detail_title);
        mBackIcon = (ImageView) mToolBar.findViewById(R.id.toolbar_back);
        mBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
