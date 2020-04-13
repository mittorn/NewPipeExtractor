package org.schabi.newpipe.extractor.services.test;

import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandlerFactory;
import org.schabi.newpipe.extractor.utils.Parser;

import java.util.List;

public class TestListLinkHandlerFactory extends ListLinkHandlerFactory {

    @Override
    public String getUrl(String id, List<String> contentFilter, String sortFilter) throws ParsingException {
        return "http://example.com/list" + id;
    }

    @Override
    public String getId(String url) throws ParsingException {
        /*if (url.startsWith("https://api.media.ccc.de/public/conferences/")) {
            return url.replace("https://api.media.ccc.de/public/conferences/", "");
        } else if (url.startsWith("https://media.ccc.de/c/")) {
            return Parser.matchGroup1("https://media.ccc.de/c/([^?#]*)", url);
        } else if (url.startsWith("https://media.ccc.de/b/")) {
            return Parser.matchGroup1("https://media.ccc.de/b/([^?#]*)", url);
        }
        throw new ParsingException("Could not get id from url: " + url);
        */
        return "test";
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
