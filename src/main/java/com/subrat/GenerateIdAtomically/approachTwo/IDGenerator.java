package com.subrat.GenerateIdAtomically.approachTwo;

import com.subrat.GenerateIdAtomically.util.FileUtil;

public class IDGenerator {
    private Integer id;

    public IDGenerator() {
        id = FileUtil.readCount();
    }

    public Integer getId() {
        synchronized ("lock") {
            id = id + 1;
            FileUtil.saveCountToFile(id);
        }
        return id;
    }

}