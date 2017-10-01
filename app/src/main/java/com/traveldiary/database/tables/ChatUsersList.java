package com.traveldiary.database.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by mohit on 29/08/17.
 */

public class ChatUsersList extends BaseTable {

    /**
     * Model class for ImageTemplate database table
     */
    private static final long serialVersionUID = -222864131214757024L;

   // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = "id",unique= true,uniqueIndex = true,id = true)
    public String id;


    @DatabaseField(columnName = "user_id" )
    private String userId;

    @DatabaseField(columnName = "is_new" )
    private String isNew;

    @DatabaseField(columnName = "last_message", indexName = "fetch_chat_index")
    private String lastMessage;

    @DatabaseField(columnName = "name", indexName = "fetch_chat_index")
    private String name;

    @DatabaseField(columnName = "last_message_timestamp")
    private long lastMessageTimestamp;

    @DatabaseField(columnName = "is_self", indexName = "fetch_chat_index")
    private int isSelf;

    @DatabaseField(columnName = "last_seen_timestamp")
    private long lastSeenTimestamp;

    @DatabaseField(columnName = "match_date")
    private long matchDate;

    @DatabaseField(columnName = "image_url" )
    private String imageUrl;

    @DatabaseField(columnName = "device_tokens" )
    private String deviceTokens;

    @DatabaseField(columnName = "is_notification_disabled_by_partner" )
    private boolean isNotificationDisabledByPartner;

    @DatabaseField(columnName = "is_notification_disabled_by_self" )
    private boolean isNotificationDisabledBySelf;

    public ChatUsersList() {

    }

    public String getId() {
        return id;
    }

    public ChatUsersList setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public ChatUsersList setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public ChatUsersList setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChatUsersList setName(String name) {
        this.name = name;
        return this;
    }

    public int isSelf() {
        return isSelf;
    }

    public ChatUsersList setSelf(int self) {
        isSelf = self;
        return this;
    }

    public long getLastSeenTimestamp() {
        return lastSeenTimestamp;
    }

    public ChatUsersList setLastSeenTimestamp(long lastSeenTimestamp) {
        this.lastSeenTimestamp = lastSeenTimestamp;
        return this;
    }

    public String getIsNew() {
        return isNew;
    }

    public ChatUsersList setIsNew(String isNew) {
        this.isNew = isNew;
        return this;
    }

    public long getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public ChatUsersList setLastMessageTimestamp(long lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
        return this;
    }


    public long getMatchDate() {
        return matchDate;
    }

    public ChatUsersList setMatchDate(long matchDate) {
        this.matchDate = matchDate;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ChatUsersList setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDeviceTokens() {
        return deviceTokens;
    }

    public ChatUsersList setDeviceTokens(String deviceTokens) {
        this.deviceTokens = deviceTokens;
        return this;
    }

    public boolean getIsNotificationDisabledByPartner() {
        return isNotificationDisabledByPartner;
    }

    public ChatUsersList setIsNotificationDisabledByPartner(boolean isNotificationDisabledByPartner) {
        this.isNotificationDisabledByPartner = isNotificationDisabledByPartner;
        return this;
    }

    public boolean getIsNotificationDisabledBySelf() {
        return isNotificationDisabledBySelf;
    }

    public ChatUsersList setIsNotificationDisabledBySelf(boolean isNotificationDisabledBySelf) {
        this.isNotificationDisabledBySelf = isNotificationDisabledBySelf;
        return this;
    }
}
