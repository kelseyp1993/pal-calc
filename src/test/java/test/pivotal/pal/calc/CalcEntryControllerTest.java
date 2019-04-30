package test.pivotal.pal.calc;

import io.pivotal.pal.calc.CalcEntry;
import io.pivotal.pal.calc.CalcEntryController;
import io.pivotal.pal.calc.CalcEntryRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CalcEntryControllerTest {
    private CalcEntryRepository calcEntryRepository;
    private CalcEntryController controller;

    @Before
    public void setUp() throws Exception {
        calcEntryRepository = mock(CalcEntryRepository.class);
        controller = new CalcEntryController(calcEntryRepository);
    }

    @Test
    public void testCreate() throws Exception {
        double operandOne = 5;
        double operandTwo = 4;
        int operator = 0; //0:Addition

        CalcEntry calcEntryToCreate = new CalcEntry(operandOne, operandTwo, operator);

        double expectedCalcResult = 9;
        CalcEntry expectedResult = new CalcEntry(operandOne, operandTwo, operator, expectedCalcResult);
        doReturn(expectedResult)
            .when(calcEntryRepository)
            .create(any(CalcEntry.class));


        ResponseEntity response = controller.create(calcEntryToCreate);

        System.out.println();
        verify(calcEntryRepository).create(calcEntryToCreate);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testList() throws Exception {
        List<CalcEntry> expected = asList(
                new CalcEntry(10, 5, 3, 2),
                new CalcEntry(10, 5, 2, 50),
                new CalcEntry(10, 5, 1, 5),
                new CalcEntry(10, 5, 0, 15)
        );
        doReturn(expected).when(calcEntryRepository).list();

        ResponseEntity<List<CalcEntry>> response = controller.list();

        verify(calcEntryRepository).list();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

}
