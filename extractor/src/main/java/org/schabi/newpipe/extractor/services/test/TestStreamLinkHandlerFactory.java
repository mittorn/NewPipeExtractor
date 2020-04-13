package org.schabi.newpipe.extractor.services.test;

import org.schabi.newpipe.extractor.exceptions.FoundAdException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.LinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;

public class TestStreamLinkHandlerFactory extends LinkHandlerFactory {

    @Override
    public String getId(String urlString) throws ParsingException {
        /*if (urlString.startsWith("https://api.media.ccc.de/public/events/") &&
                !urlString.contains("?q=")) {
            return urlString.substring(39); //remove api…/public/events part
        }

        URL url;
        try {
            url = Utils.stringToURL(urlString);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("The given URL is not valid");
        }

        String path = url.getPath();
        // remove leading "/" of URL-path if URL-path is given
        if (!path.isEmpty()) {
            path = path.substring(1);
        }

        if (path.startsWith("v/")) {
            return path.substring(2);
        }*/
        return urlString;

        throw new ParsingException("Could not get id from url: " + url);
    }

    @Override
    public String getUrl(String id) throws ParsingException {
        return id;// "https://api.media.ccc.de/public/events/" + id;
    }

    @Override
    public boolean onAcceptUrl(String url) throws ParsingException {
        try {
            getId(url);
            return true;
        } catch (ParsingException e) {
            return false;
        }
    }
}
