package org.functionalkoans.forscala;

import java.util.List;

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 5/11/11
 * Time: 10:55 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class SomeJavaClass {
    public int findSizeOfRawType(List list) {
        return list.size();
    }

    public int findSizeOfUnknownType(List<?> list) {
        return list.size();
    }
}
