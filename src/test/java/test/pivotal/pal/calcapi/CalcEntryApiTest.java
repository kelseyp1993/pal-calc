package test.pivotal.pal.calcapi;

import com.jayway.jsonpath.DocumentContext;
import io.pivotal.pal.calc.CalcEntry;
import io.pivotal.pal.calc.PalCalcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static com.jayway.jsonpath.JsonPath.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PalCalcApplication.class, webEnvironment = RANDOM_PORT)
public class CalcEntryApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final double operandOne = 5;
    private final double operandTwo = 4;
    private int operator = 0; //0:Addition

    private CalcEntry calcEntryToCreate = new CalcEntry(operandOne, operandTwo, operator);

    @Test
    public void testCreate() throws Exception {
        ResponseEntity<String> createResponse = restTemplate.postForEntity("/calc-entries", calcEntryToCreate, String.class);


        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext createJson = parse(createResponse.getBody());
        assertThat(createJson.read("$.operandOne", double.class)).isNotNaN();
        assertThat(createJson.read("$.operandOne", double.class)).isEqualTo(operandOne);
        assertThat(createJson.read("$.operandTwo", double.class)).isNotNaN();
        assertThat(createJson.read("$.operandTwo", double.class)).isEqualTo(operandTwo);
        assertThat(createJson.read("$.operator", int.class)).isBetween(0,4);
        assertThat(createJson.read("$.operator", int.class)).isEqualTo(operator);
        assertThat(createJson.read("$.result", double.class)).isNotNaN();
    }

    @Test
    public void testList() throws Exception {
        double result = createCalcEntry();


        ResponseEntity<String> listResponse = restTemplate.getForEntity("/calc-entries", String.class);


        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext listJson = parse(listResponse.getBody());

        Collection calcEntries = listJson.read("$[*]", Collection.class);
        assertThat(calcEntries.size()).isEqualTo(1);

        double readResult = listJson.read("$[0].result", double.class);
        assertThat(readResult).isEqualTo(result);
    }

    private double createCalcEntry() {
        HttpEntity<CalcEntry> entity = new HttpEntity<>(calcEntryToCreate);

        ResponseEntity<CalcEntry> response = restTemplate.exchange("/calc-entries", HttpMethod.POST, entity, CalcEntry.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        return response.getBody().getResult();
    }
}
