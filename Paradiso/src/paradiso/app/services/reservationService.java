package paradiso.app.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import paradiso.app.dao.ReservationsDAO;
import paradiso.app.entity.ReservationVO;

@Path("/reservations")
public class reservationService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReservationVO> getReservations(){
		ReservationsDAO dao = new ReservationsDAO();
		List<ReservationVO> reservations = dao.getReservations();
		
		return reservations;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createReservation(ReservationVO reservation){
		ReservationsDAO dao = new ReservationsDAO();
		reservation = dao.createReservation(reservation);
	}
	
	/*@Path("/edit")
	public void editReservation(){
		
	}*/
}

/*@Path("/table")
class tableService {

	@Path("/assign")
	public void assignTable(){
		
	}
	
}*/
