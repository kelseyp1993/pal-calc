package io.pivotal.pal.calc;

import java.util.List;

public interface CalcEntryRepository {
    public CalcEntry create(CalcEntry calcEntry);
    public List<CalcEntry> list();
}
