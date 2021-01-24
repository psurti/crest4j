package com.lotuslabs.rest.model.actions;

import com.lotuslabs.rest.interfaces.IRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
class PutAction extends RestAction {
    public static final String NAME = "put";

    public PutAction(String name, Properties properties) {
        super(name, properties);
    }

    @Override
    public Map<String,?> execute(Map<String, Object> context,
                                 IRestClient<Map<String,?>, String> restClient){
        String eTag = getETag(context);
        return restClient.put(getURI(context), getBodyString(context), eTag, getJsonPathParams());
    }

    private String getETag(Map<String,Object> context) {
        String eTag = null;
        final List<Object> valueList = (List<Object>) context.remove(HttpHeaders.ETAG);
        if ( !valueList.isEmpty() ) {
            final Object o = valueList.get(0);
            if (o != null) {
                eTag = o.toString();
                eTag = eTag.substring(1, eTag.length() - 1); //hack
                log.warn( "etag:{}", eTag);
            }
        }
        return eTag;
    }
}
