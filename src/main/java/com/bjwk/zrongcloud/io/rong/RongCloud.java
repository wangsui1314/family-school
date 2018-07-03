/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2017-06-05
 * <p>
 * v2.0.1
 */
package com.bjwk.zrongcloud.io.rong;

import java.util.concurrent.ConcurrentHashMap;

import com.bjwk.zrongcloud.io.rong.methods.chatroom.*;
import com.bjwk.zrongcloud.io.rong.methods.conversation.Conversation;
import com.bjwk.zrongcloud.io.rong.methods.group.Group;
import com.bjwk.zrongcloud.io.rong.methods.message.Message;
import com.bjwk.zrongcloud.io.rong.methods.sensitive.SensitiveWord;
import com.bjwk.zrongcloud.io.rong.methods.sensitive.Wordfilter;
import com.bjwk.zrongcloud.io.rong.methods.user.User;
import com.bjwk.zrongcloud.io.rong.util.HostType;

public class RongCloud {

	private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String, RongCloud>();

	public User user;
	public Message message;
	public Wordfilter wordfilter;
	public SensitiveWord sensitiveword;
	public Group group;
	public Chatroom chatroom;
	public Conversation conversation;
	private HostType apiHostType = new HostType("http://api.cn.ronghub.com");
	private HostType smsHostType = new HostType("http://api.sms.ronghub.com");

	public HostType getApiHostType() {
		return apiHostType;
	}

	public void setApiHostType(HostType apiHostType) {
		this.apiHostType = apiHostType;
	}

	public HostType getSmsHostType() {
		return smsHostType;
	}

	public void setSmsHostType(HostType smsHostType) {
		this.smsHostType = smsHostType;
	}

	private RongCloud(String appKey, String appSecret) {
		user = new User(appKey, appSecret);
		user.setRongCloud(this);
		message = new Message(appKey, appSecret);
		message.setRongCloud(this);
		wordfilter = new Wordfilter(appKey, appSecret);
		wordfilter.setRongCloud(this);
		sensitiveword = new SensitiveWord(appKey, appSecret);
		sensitiveword.setRongCloud(this);
		group = new Group(appKey, appSecret);
		group.setRongCloud(this);
		chatroom = new Chatroom(appKey, appSecret);
		chatroom.setRongCloud(this);
		conversation = new Conversation(appKey, appSecret);
		conversation.setRongCloud(this);

	}

	public static RongCloud getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
		}
		return rongCloud.get(appKey);
	}

	public static RongCloud getInstance(String appKey, String appSecret, String api) {
		if (null == rongCloud.get(appKey)) {
			RongCloud rc = new RongCloud(appKey, appSecret);
			if (api != null && api.trim().length() > 0) {
				rc.setApiHostType(new HostType(api));
			}
			rongCloud.putIfAbsent(appKey, rc);
		}
		return rongCloud.get(appKey);
	}

}