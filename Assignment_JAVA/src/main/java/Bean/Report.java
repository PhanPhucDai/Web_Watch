package Bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "Report.favoriteByYear",
							procedureName = "spFavoriteByYear",
							resultClasses = {Report.class},
							parameters = @StoredProcedureParameter(name="year", type = Integer.class)
	)
})

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	@Id
	String group;
	Long likes;
	Date newest;
	Date oldest;
}
