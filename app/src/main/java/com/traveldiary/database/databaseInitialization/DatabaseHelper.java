package com.traveldiary.database.databaseInitialization;
 
import java.sql.SQLException;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.traveldiary.R;
import com.traveldiary.database.tables.BaseTable;
import com.traveldiary.database.tables.ChatUsersList;
import com.traveldiary.database.tables.FbProfileTable;
import com.traveldiary.database.tables.ImageTemplate;
import com.traveldiary.database.tables.NotificationSubscriptionTable;
import com.traveldiary.database.tables.ProfileTable;
import com.traveldiary.database.tables.ServiceDataCache;

import com.traveldiary.database.tables.FilterTable;
import com.traveldiary.database.tables.SuperLikedTable;
import com.traveldiary.database.tables.UserImageTable;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
 
/**
 * Database helper which creates and upgrades the database and provides the DAOs for the app.
 * 
 * 
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
 
	/************************************************
	 * Suggested Copy/Paste code. Everything from here to the done block.
	 ************************************************/
 
	private static final String DATABASE_NAME = "HotieeNotiee.db";
	private static final int DATABASE_VERSION = 1; 
 
	private Dao<ImageTemplate, Integer> templateDao;
	private Dao<ServiceDataCache, Integer> serviceCacheDao;
	private Dao<UserImageTable, Integer> userImagesDao;
	private Dao<ProfileTable, Integer> profileDao;
	private Dao<FilterTable, Integer> filterDao;
	private Dao<FbProfileTable, Integer> fbProfileDao;
	private Dao<ChatUsersList, Integer> chatUsersListDao;


	private Dao<SuperLikedTable, String> spLikedDao;
	private Dao<NotificationSubscriptionTable, Integer> notificationSubscriptionDao;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}
 
	/************************************************
	 * Suggested Copy/Paste Done
	 ************************************************/
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {

		// Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
		try {
            TableUtils.createTable(connectionSource, ChatUsersList.class);
        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, ImageTemplate.class);

        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, ServiceDataCache.class);
        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, UserImageTable.class);
        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, ProfileTable.class);
        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, FilterTable.class);
        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource,SuperLikedTable.class);
        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, NotificationSubscriptionTable.class);

        }catch (SQLException e){

        }
		try {
            TableUtils.createTable(connectionSource, FbProfileTable.class);
        }catch (SQLException e){

        }


		//TableUtils.createTable(connectionSource, Data.class);

	}



	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {

		// In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked
		//automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
		// existing database etc.

		try {
			TableUtils.dropTable(connectionSource, ImageTemplate.class, true);
			TableUtils.dropTable(connectionSource, ServiceDataCache.class, true);
			TableUtils.dropTable(connectionSource, UserImageTable.class, true);
			TableUtils.dropTable(connectionSource, ProfileTable.class, true);
			TableUtils.dropTable(connectionSource, FilterTable.class, true);
			TableUtils.dropTable(connectionSource, FbProfileTable.class, true);
			TableUtils.dropTable(connectionSource, SuperLikedTable.class, true);
			TableUtils.dropTable(connectionSource, NotificationSubscriptionTable.class, true);
			TableUtils.dropTable(connectionSource, ChatUsersList.class, true);
			onCreate(sqliteDatabase, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//			TableUtils.dropTable(connectionSource, Data.class, true);


	}
	
	// Create the getDao methods of all database tables to access those from android code.
	// Insert, delete, read, update everything will be happened through DAOs
 
	public Dao<ImageTemplate, Integer> getTemplateDao() throws SQLException {
		if (templateDao == null) {
			templateDao = getDao(ImageTemplate.class);
		}
		return templateDao;
	}
 
	public Dao<ServiceDataCache, Integer> getServiceDataCacheDao() throws SQLException {
		if (serviceCacheDao == null) {
			serviceCacheDao = getDao(ServiceDataCache.class);
		}
		return serviceCacheDao;
	}
	public Dao<UserImageTable, Integer> getUserImagesDao() throws SQLException {
		if ( userImagesDao== null) {
			userImagesDao = getDao(UserImageTable.class);
		}
		return userImagesDao;
	}
	public Dao<ProfileTable, Integer> getProfileDao() throws SQLException {
		if ( profileDao== null) {
			profileDao = getDao(ProfileTable.class);
		}
		return profileDao;
	}
	public Dao<FilterTable, Integer> getFilterDao() throws SQLException {
		if ( filterDao== null) {
			filterDao = getDao(FilterTable.class);
		}
		return filterDao;
	}
	public Dao<FbProfileTable, Integer> getFbProfileDao() throws SQLException {
		if ( fbProfileDao== null) {
			fbProfileDao = getDao(FbProfileTable.class);
		}
		return fbProfileDao;
	}

	public Dao<SuperLikedTable, String> getSpLikedDao() {
		if(spLikedDao==null){
			try {
				spLikedDao=getDao(SuperLikedTable.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return spLikedDao;
	}

	public Dao<NotificationSubscriptionTable, Integer> getNotificationSubscriptionDao() throws SQLException {
		if ( notificationSubscriptionDao== null) {
			notificationSubscriptionDao = getDao(NotificationSubscriptionTable.class);
		}
		return notificationSubscriptionDao;
	}
    public Dao<ChatUsersList, Integer> getChatListDao() throws SQLException {
        if ( chatUsersListDao== null) {
            chatUsersListDao = getDao(ChatUsersList.class);
        }
        return chatUsersListDao;
    }
}