package com.lotuslabs.rest.model.actions;

import com.lotuslabs.rest.interfaces.IRestClient;

import java.util.Map;
import java.util.Properties;

class DeleteAction extends RestAction {
    public static final String NAME = "delete";

    public DeleteAction(String name, Properties properties) {
        super(name, properties);
    }

    public Map<String,?> execute(Map<String, Object> context,
                                 IRestClient<Map<String,?>, String> restClient) {
        return restClient.delete(getURI(context), getJsonPathParams());
    }
}