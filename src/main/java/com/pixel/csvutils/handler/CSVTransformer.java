package com.pixel.csvutils.handler;

import com.pixel.model.BaseModel;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 01.07.2021
 */

interface CSVTransformer {
    List<? extends BaseModel> fromFileToRecordsList(InputStream inputStream);
}
