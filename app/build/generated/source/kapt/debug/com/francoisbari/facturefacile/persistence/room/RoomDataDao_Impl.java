package com.francoisbari.facturefacile.persistence.room;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RoomDataDao_Impl implements RoomDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserInputEntity> __insertionAdapterOfUserInputEntity;

  private final EntityDeletionOrUpdateAdapter<UserInputEntity> __updateAdapterOfUserInputEntity;

  public RoomDataDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserInputEntity = new EntityInsertionAdapter<UserInputEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `UserInputEntity` (`uid`,`nbOfDays`,`tjm`,`monthId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserInputEntity entity) {
        statement.bindLong(1, entity.getUid());
        statement.bindLong(2, entity.getNbOfDays());
        statement.bindLong(3, entity.getTjm());
        statement.bindLong(4, entity.getMonthId());
      }
    };
    this.__updateAdapterOfUserInputEntity = new EntityDeletionOrUpdateAdapter<UserInputEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `UserInputEntity` SET `uid` = ?,`nbOfDays` = ?,`tjm` = ?,`monthId` = ? WHERE `uid` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserInputEntity entity) {
        statement.bindLong(1, entity.getUid());
        statement.bindLong(2, entity.getNbOfDays());
        statement.bindLong(3, entity.getTjm());
        statement.bindLong(4, entity.getMonthId());
        statement.bindLong(5, entity.getUid());
      }
    };
  }

  @Override
  public long saveData(final UserInputEntity userInputEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfUserInputEntity.insertAndReturnId(userInputEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateData(final UserInputEntity userInputEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserInputEntity.handle(userInputEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public UserInputEntity loadLatestMonth() {
    final String _sql = "SELECT * FROM UserInputEntity ORDER BY monthId DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfNbOfDays = CursorUtil.getColumnIndexOrThrow(_cursor, "nbOfDays");
      final int _cursorIndexOfTjm = CursorUtil.getColumnIndexOrThrow(_cursor, "tjm");
      final int _cursorIndexOfMonthId = CursorUtil.getColumnIndexOrThrow(_cursor, "monthId");
      final UserInputEntity _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpNbOfDays;
        _tmpNbOfDays = _cursor.getInt(_cursorIndexOfNbOfDays);
        final int _tmpTjm;
        _tmpTjm = _cursor.getInt(_cursorIndexOfTjm);
        final int _tmpMonthId;
        _tmpMonthId = _cursor.getInt(_cursorIndexOfMonthId);
        _result = new UserInputEntity(_tmpUid,_tmpNbOfDays,_tmpTjm,_tmpMonthId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public UserInputEntity getDataFromMonth(final int monthId) {
    final String _sql = "SELECT * FROM UserInputEntity WHERE monthId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, monthId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfNbOfDays = CursorUtil.getColumnIndexOrThrow(_cursor, "nbOfDays");
      final int _cursorIndexOfTjm = CursorUtil.getColumnIndexOrThrow(_cursor, "tjm");
      final int _cursorIndexOfMonthId = CursorUtil.getColumnIndexOrThrow(_cursor, "monthId");
      final UserInputEntity _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final int _tmpNbOfDays;
        _tmpNbOfDays = _cursor.getInt(_cursorIndexOfNbOfDays);
        final int _tmpTjm;
        _tmpTjm = _cursor.getInt(_cursorIndexOfTjm);
        final int _tmpMonthId;
        _tmpMonthId = _cursor.getInt(_cursorIndexOfMonthId);
        _result = new UserInputEntity(_tmpUid,_tmpNbOfDays,_tmpTjm,_tmpMonthId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Integer> getYearlyTotal() {
    final String _sql = "SELECT SUM(tjm * nbOfDays) FROM UserInputEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"UserInputEntity"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getYearlyTotalValue() {
    final String _sql = "SELECT SUM(tjm * nbOfDays) FROM UserInputEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
