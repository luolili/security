package com.luo.core.social.qq.connect;

import com.luo.core.social.qq.api.QQ;
import com.luo.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return true;//qq 可用
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getQQUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
