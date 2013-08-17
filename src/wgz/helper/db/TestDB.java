package wgz.helper.db;

import java.io.FileInputStream;
import java.util.Properties;

import wgz.helper.io.FileIO;

public class TestDB {

	public static void main(String[] args) throws Exception {
		Properties pp = new Properties();
		pp.loadFromXML(new FileInputStream(FileIO.findSiblingResource(TestDB.class, "properties.xml")));
		final String DB = pp.getProperty("DB_NOW");
		final String driver = pp.getProperty(DB + "_DRIVER");
		final String user = pp.getProperty(DB + "_USER");
		final String pass = pp.getProperty(DB + "_PASS");
		final String URL = pp.getProperty(DB + "_URL");
		final String cleanUp = pp.getProperty(DB + "_CLEANUP");

		DBFrameWork db = new NOPOOL(driver, URL, user, pass, cleanUp);
		db.batchUpdate(FileIO.findRootResource(TestDB.class, "createDB.sql"));
		db.query("select * from user", true);
		db.shutdown(null);
	}

}
