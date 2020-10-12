package com.idealista.adrankingchallenge.domain.ad;

import com.idealista.adrankingchallenge.domain.ad.shared.StringValueObject;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

public class AdDescription extends StringValueObject {

  private final static Integer MINIMUN_WORDS_MEDIUM_DESCRIPTION = 20;
  private final static Integer MAXIMUN_WORDS_MEDIUM_DESCRIPTION = 49;

  private final DescriptionKeywords descriptionKeywords = new DescriptionKeywords(
      new String[]{"Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático"});

  public AdDescription(String value) {
    super(value);
  }

  public boolean hasDescription() {
    return StringUtils.isNotBlank(value());
  }

  public boolean isMedium() {
    if (!hasDescription()) {
      return false;
    }
    int words = new StringTokenizer(value()).countTokens();
    return words >= MINIMUN_WORDS_MEDIUM_DESCRIPTION && words <= MAXIMUN_WORDS_MEDIUM_DESCRIPTION;
  }

  public boolean isLong() {
    if (!hasDescription()) {
      return false;
    }
    int words = new StringTokenizer(value()).countTokens();
    return words > MAXIMUN_WORDS_MEDIUM_DESCRIPTION;
  }

  public Integer numberOfOccurrencesWithKeywordsInTheDescription() {
    if (!hasDescription()) {
      return 0;
    }
    long numberOfOccurrences = Arrays.stream(value().split(StringUtils.SPACE))
                                     .filter(descriptionWords -> descriptionKeywords.contains(descriptionWords))
                                     .distinct().count();
    return Math.toIntExact(numberOfOccurrences);
  }

  private class DescriptionKeywords extends ArrayList<String> {

    private final Collator instance = Collator.getInstance();

    public DescriptionKeywords(String[] keywords) {
      super(Arrays.asList(keywords));
      instance.setStrength(Collator.PRIMARY);
    }

    @Override
    public boolean contains(Object word) {
      String paramWord = (String) word;
      for (String keyword : this) {
        if (instance.compare(keyword, paramWord) == 0) {
          return true;
        }
      }
      return false;
    }
  }
}
