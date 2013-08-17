package wgz.helper.db;

import java.io.File;

public interface DBFrameWork {
	public boolean query(String sql, boolean ShowResult);

	public void shutdown(File file);

	public void update(String sql);

	public void batchUpdate(File file);

}
