package org.guides.generator;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

import java.io.IOException;

public class DaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(2, "org.guides.android_example.app.dao");
        schema.enableKeepSectionsByDefault();

        Entity student = schema.addEntity("Student");
        student.addStringProperty("name").notNull();
        student.addIdProperty().notNull().primaryKey().autoincrement();
        student.addIntProperty("age");
        student.implementsSerializable();

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, args[0]);
    }
}
