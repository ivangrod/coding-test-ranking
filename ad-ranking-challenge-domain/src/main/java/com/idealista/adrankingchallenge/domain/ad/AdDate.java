package com.idealista.adrankingchallenge.domain.ad;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class AdDate {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter
      .ofPattern("MM/dd/yyyy - HH:mm:ss");

  private final LocalDateTime irrelevantSince;

  public AdDate() {
    this.irrelevantSince = null;
  }

  public AdDate(Date value) {
    this.irrelevantSince =
        value != null ? value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
  }

  public String toStringFormat() {
    ZoneId systemZone = ZoneId.systemDefault();
    return ZonedDateTime.ofInstant(irrelevantSince.toInstant(ZoneOffset.UTC), systemZone)
                        .format(TIME_FORMATTER);
  }

  public Date toDate() {
    return irrelevantSince != null ? Date.from(irrelevantSince.atZone(ZoneId.systemDefault()).toInstant())
        : null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdDate adDate = (AdDate) o;
    return Objects.equals(irrelevantSince, adDate.irrelevantSince);
  }

  @Override
  public int hashCode() {
    return Objects.hash(irrelevantSince);
  }

  @Override
  public String toString() {
    return "AdDate{" +
        "irrelevantSince=" + irrelevantSince +
        '}';
  }
}
