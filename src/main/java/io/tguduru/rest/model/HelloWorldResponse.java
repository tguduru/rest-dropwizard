package io.tguduru.rest.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for hello world demo
 * @author Thirupathi Reddy Guduru
 * @modified Jan 18, 2015
 */
public class HelloWorldResponse {
    @Length(max = 10)
    private String content;
    private long id;

    /**
     *
     */
    public HelloWorldResponse() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param content
     * @param id
     */
    public HelloWorldResponse(final String content, final long id) {
        this.content = content;
        this.id = id;
    }

    /**
     * @return the content.
     */
    @JsonProperty
    public String getContent() {
        return content;
    }

    /**
     * @return the id.
     */
    @JsonProperty
    public long getId() {
        return id;
    }

}
