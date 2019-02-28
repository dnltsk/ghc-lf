package org.lazyfingerz.ghlf.model;

import lombok.Value;

import java.util.List;

/**
 * The type Model.
 */
@Value
public class LfImage {

    private Arrangement arrangement;
    private List<String> tags;

}
