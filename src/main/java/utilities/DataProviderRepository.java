package utilities;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import accelarators.ActionDriver;

public class DataProviderRepository extends ActionDriver {


	@DataProvider(name = "IUTestData") 
	public Object[][] dataProviderMethod(Method method) throws MyOwnException {

		log.info("INTEND TO READ THE TEST DATA USING DATAPROVIDER FROM EXCEL WORKBOOK");
		Object[][] testDataArray = null;
		try {
			if (method.getName().equals("IU_TC1")) {
				testDataArray = Excel.getTestDataAsTwoDimesionalObjectArray(
						System.getProperty("user.dir") + configProps.getProperty("TestDatapath"),
						configProps.getProperty("DataProviderExcelName"),
						configProps.getProperty("DataProviderSheetName"));
			}
		} catch (Exception exp) {
			log.error(exp.getMessage());
			throwException("UNABLE TO EXECUTE DATA PROVIDER SECTION FROM THE METHOD dataProviderMethod\n"
					+ exp.getMessage() + "\n");
		}
		log.info("SUCCESSFULLY READ THE TEST DATA FOR (" + method
				+ ") SECURITY TYPE USING DATAPROVIDER FROM EXCEL WORKBOOK");
		return testDataArray;
	}

}
