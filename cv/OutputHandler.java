package Servants;

import DBClasses.WritableToDB;

public interface OutputHandler {
    public void writeTheUserToTheFile(final WritableToDB user);
}
