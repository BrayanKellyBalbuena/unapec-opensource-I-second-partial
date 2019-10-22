package edu.unapec.shoppingorders.models;

import java.util.List;

public class ResponseResult<T> {
    List<T> data;
    List<String> errors;
    int currentPage;
    int totalPage;
    boolean hasNext;
}
