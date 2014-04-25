package com.airhacks.enhydrator.scenarios;

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

import com.airhacks.enhydrator.Pump;
import com.airhacks.enhydrator.in.CSVSource;
import com.airhacks.enhydrator.in.Entry;
import com.airhacks.enhydrator.in.Source;
import com.airhacks.enhydrator.in.VirtualSinkSource;
import com.airhacks.enhydrator.out.LogSink;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class CSVImportTest {

    /**
     * 1997;Ford;E350;2,34
     */
    @Test
    public void copy() {
        Source source = new CSVSource("./src/test/files/cars.csv", ";", true);
        VirtualSinkSource vss = new VirtualSinkSource();
        Pump pump = new Pump.Engine().
                from(source).
                to(vss).
                to(new LogSink()).
                build();
        pump.start();

        int numberOfRows = vss.getNumberOfRows();
        assertTrue(numberOfRows > 0);
        Iterable<List<Entry>> query = vss.query(null);
        boolean foundFord = false;
        for (List<Entry> list : query) {
            assertThat(list.size(), is(4));
            if ("Ford".equals(list.get(1).getValue())) {
                foundFord = true;
            }
        }
        assertTrue(foundFord);
    }

}
