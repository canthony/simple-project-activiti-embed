/*
 * Copyright (c) 2001-2015 HPD Software Ltd.
 */
package com.example.embedsample.util;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.util.List;
import java.util.Map;

/**
 * Creates instances of Guava Ordering, based on registered Orderings.
 * <p/>
 * Add a specific ordering by using {@link #addOrdering(String, Ordering)}; typically, this
 * will be an "ascending" ordering.
 * <br/>
 * You can retrieve an individual order by using {@link #getOrdering(String)}, passing it
 * an ordering description.
 *
 * If it is a simple "name", the corresponding ordering is returned.
 * If it is prefixed with -, the ordering is reversed.
 * If it is prefixed with "+", the
 *
 * The description can have multiple +-names, separated with commas.
 * e.g. {@code
 * getOrdering("country,-age,+name")
 * } which will order by "country ascending, followed by age descending, followed by name ascending.
 *
 * <p></p>
 * com.example.embedsample.domain.OrderingHelper, created on 30/11/2015 10:28 <p>
 * @author Charles
 */
public class OrderingFactory<T> {

  public static final Splitter DESCRIPTOR_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

  protected Map<String, Ordering<T>> nameToOrdering = Maps.newHashMap();
  private String defaultName;


  /**
   * Adds a named ordering
   *
   * @param name
   * @param ordering
   */
  public void addOrdering(String name, Ordering<T> ordering) {
    nameToOrdering.put(name, ordering);
  }

  /**
   * Creates an ordering, as specified by the descriptor
   *
   * @param descriptorAsString
   * @return
   */
  public Ordering<T> getOrdering(String descriptorAsString) {
    List<String> descriptors = DESCRIPTOR_SPLITTER.splitToList(descriptorAsString);

    List<Ordering<T>> orderings = Lists.transform(descriptors, new Function<String, Ordering<T>>() {
      @Override
      public Ordering<T> apply(String input) {
        Pair pair = createPair(input);
        Ordering<T> ordering = getOrdering(pair);

        // Couldn't find a matching ordering : look up the default, if we have one
        if (ordering == null && !Strings.isNullOrEmpty(defaultName)) {
          ordering = getOrdering(defaultName);
        }

        // Still no ordering : use an arbitrary ordering
        if (ordering == null) {
          //noinspection unchecked
          ordering = (Ordering<T>) Ordering.arbitrary();
        }
        return ordering;
      }
    });

    if (orderings.size() == 1) {
      return orderings.get(0);
    } else {
      return Ordering.compound(orderings);
    }
  }

  /**
   * Creates an Pair for looking up orderings. <p/>
   * example  => acscending=true, name="example" <br/>
   * -example => ascending=false, name="example" <br/>
   * + example => acscending=true, name="example" <br/>
   * @param descriptor
   * @return
   */
  protected Pair createPair(String descriptor) {
    Preconditions.checkNotNull(descriptor, "descriptor cannot be null");

    if (descriptor.startsWith("+") || descriptor.startsWith("-")) {
      return new Pair(descriptor.substring(1).trim(), descriptor.startsWith("+"));
    } else {
      return new Pair(descriptor);
    }
  }

  /**
   * Given a pair, return a corresponding ordering; if the pair is
   * descending, the ordering is reversed.
   *
   * If the named ordering couldn't be found, null is returned.
   * @param pair
   * @return
   */
  protected Ordering<T> getOrdering(Pair pair) {
    Ordering<T> ordering = nameToOrdering.get(pair.getName());
    if (ordering == null) {
      return null;
    }
    return pair.isAscending() ? ordering : ordering.reverse();
  }

  public String getDefaultName() {
    return defaultName;
  }

  public void setDefaultName(String defaultName) {
    this.defaultName = defaultName;
  }

  /**
   * Represents an ordering to return
   */
  protected class Pair {
    /**
     * Name of the ordering
     */
    protected String name;

    /**
     * Should the ordering be ascending?
     */
    protected boolean ascending = true;

    public Pair(String name) {
      this(name, true);
    }

    public Pair(String name, boolean ascending) {
      this.name = name;
      this.ascending = ascending;
    }

    public boolean isAscending() {
      return ascending;
    }

    public String getName() {
      return name;
    }
  }
}
