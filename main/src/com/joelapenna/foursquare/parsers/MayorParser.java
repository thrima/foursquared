/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquare.parsers;

import com.joelapenna.foursquare.Foursquare;
import com.joelapenna.foursquare.error.FoursquareError;
import com.joelapenna.foursquare.error.FoursquareParseException;
import com.joelapenna.foursquare.types.Mayor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import java.io.IOException;

/**
 * Auto-generated: 2009-07-19 11:19:50.884696
 *
 * @author Joe LaPenna (joe@joelapenna.com)
 * @param <T>
 */
public class MayorParser extends AbstractParser<Mayor> {
    private static final String TAG = "MayorParser";
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;

    @Override
    public Mayor parseInner(XmlPullParser parser) throws XmlPullParserException, IOException,
            FoursquareError, FoursquareParseException {
        try {
            parser.require(XmlPullParser.START_TAG, null, "mayor");
        } catch (XmlPullParserException e) {
            if (parser.getName().equals("error")) {
                throw new FoursquareError(parser.getText());
            }
        }

        Mayor mayor = new Mayor();

        while (parser.nextTag() == XmlPullParser.START_TAG) {
            if (DEBUG) Log.d(TAG, "Tag Name: " + String.valueOf(parser.getName()));

            String name = parser.getName();
            if ("checkins".equals(name)) {
                mayor.setCheckins(parser.nextText());

            } else if ("message".equals(name)) {
                mayor.setMessage(parser.nextText());

            } else if ("type".equals(name)) {
                mayor.setType(parser.nextText());

            } else {
                // Consume something we don't understand.
                if (DEBUG) Log.d(TAG, "Found tag that we don't recognize: " + name);
                skipSubTree(parser);
            }
        }
        return mayor;
    }
}