package test.pivotal.pal.calc;

import io.pivotal.pal.calc.InMemoryCalcEntryRepository;
import io.pivotal.pal.calc.CalcEntry;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryCalcEntryRepositoryTest {
    @Test
    public void create() throws Exception {
        InMemoryCalcEntryRepository repo = new InMemoryCalcEntryRepository();

        double operandOne = 5;
        double operandTwo = 4;
        int operator = 0; //0:Addition

        CalcEntry createdCalcEntry = repo.create(new CalcEntry(operandOne, operandTwo, operator));

        double expectedCalcResult = 9;
        CalcEntry expected = new CalcEntry(operandOne, operandTwo, operator, expectedCalcResult);
        assertThat(createdCalcEntry).isEqualTo(expected);

        CalcEntry lastEntry = repo.list().get(0);
        assertThat(lastEntry).isEqualTo(expected);
    }

    @Test
    public void list() throws Exception {
        InMemoryCalcEntryRepository repo = new InMemoryCalcEntryRepository();
        repo.create(new CalcEntry(10, 5, 0));
        repo.create(new CalcEntry(10, 5, 1));
        repo.create(new CalcEntry(10, 5, 2));
        repo.create(new CalcEntry(10, 5, 3));

        List<CalcEntry> expected = asList(
                new CalcEntry(10, 5, 3, 2),
                new CalcEntry(10, 5, 2, 50),
                new CalcEntry(10, 5, 1, 5),
                new CalcEntry(10, 5, 0, 15)
        );
        assertThat(repo.list()).isEqualTo(expected);
    }

}
