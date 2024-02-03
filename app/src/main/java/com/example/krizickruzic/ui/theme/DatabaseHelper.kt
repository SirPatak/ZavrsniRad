import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class GameDataHelper(private val context: Context) {
    fun saveGameData(playerTurn: Boolean, roundAI: Int, xPobjedeAI: Int, oPobjedeAI: Int, forfeit: Int) {
        val databaseHelper = DatabaseHelper(context)
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_PLAYER_TURN, if (playerTurn) 1 else 0)
            put(DatabaseHelper.COLUMN_ROUND_AI, roundAI)
            put(DatabaseHelper.COLUMN_XPOBJEDE_AI, xPobjedeAI)
            put(DatabaseHelper.COLUMN_OPOBJEDE_AI, oPobjedeAI)
            put(DatabaseHelper.COLUMN_FORFEIT, forfeit)
        }
        val newRowId = db?.insert(DatabaseHelper.TABLE_NAME, null, values)
        db?.close()
    }
}

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME +
            "(" + COLUMN_PLAYER_TURN + " INTEGER," +
            COLUMN_ROUND_AI + " INTEGER," +
            COLUMN_XPOBJEDE_AI + " INTEGER," +
            COLUMN_OPOBJEDE_AI + " INTEGER," +
            COLUMN_FORFEIT + " INTEGER)")

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TicTacToe.db"

        const val TABLE_NAME = "game_data"
        const val COLUMN_PLAYER_TURN = "player_turn"
        const val COLUMN_ROUND_AI = "round_ai"
        const val COLUMN_XPOBJEDE_AI = "xPobjedeAI"
        const val COLUMN_OPOBJEDE_AI = "oPobjedeAI"
        const val COLUMN_FORFEIT = "forfeit"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
}