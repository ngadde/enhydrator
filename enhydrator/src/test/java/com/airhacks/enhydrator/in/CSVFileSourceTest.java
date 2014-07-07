package com.airhacks.enhydrator.in;

/*
 * #%L
 * enhydrator
 * %%
 * Copyright (C) 2014 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class CSVFileSourceTest extends CSVSourceValidation {

    @Before
    public void init() throws FileNotFoundException {
        this.cut = new CSVFileSource("./src/test/files/cars.csv", ";", "UTF-8", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileDoesNotExist() {
        new CSVFileSource("does/NOT/exist", ";", "UTF-8", true);
    }

    @Override
    public Source getSource(final String fileName) {
        return new CSVFileSource(fileName, ";", "UTF-8", true);
    }

}
