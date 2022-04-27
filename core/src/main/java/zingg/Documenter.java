package zingg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import zingg.client.ZinggClientException;
import zingg.client.ZinggOptions;
import zingg.documenter.ModelDocumenter;
import zingg.documenter.ColumnDocumenter;

public class Documenter extends ZinggBase {

	protected static String name = "zingg.Documenter";
	public static final Log LOG = LogFactory.getLog(Documenter.class);

	public Documenter() {
		setZinggOptions(ZinggOptions.GENERATE_DOCS);
 	}

	public void execute() throws ZinggClientException {
		try {
			LOG.info("Documenter starts");
			// Marked records details
			ModelDocumenter colDoc = new ModelDocumenter(spark, args);
			colDoc.process();
			// Stop Words generation
			ColumnDocumenter stopWordDoc = new ColumnDocumenter(spark, args);
			stopWordDoc.process();
			LOG.info("Documenter finishes");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZinggClientException(e.getMessage());
		}
	}
}
