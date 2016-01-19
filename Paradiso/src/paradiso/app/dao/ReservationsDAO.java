package paradiso.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import paradiso.app.entity.ReservationVO;
import paradiso.app.utils.DBConnector;

public class ReservationsDAO {

	public List<ReservationVO> getReservations() {

		List<ReservationVO> reservations = new ArrayList<ReservationVO>();

		Connection con = DBConnector.connect();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM reservations");
			rs = ps.executeQuery();

			while (rs.next()) {
				ReservationVO reservation = new ReservationVO();

				reservation.setId(rs.getInt("ID"));
				reservation.setConfNumber(rs.getString("CONF_NO"));
				reservation
						.setReservationDate(rs.getString("RESERVATION_DATE"));
				reservation
						.setReservationTime(rs.getString("RESERVATION_TIME"));
				reservation.setFirstName(rs.getString("FIRST_NAME"));
				reservation.setLastName(rs.getString("LAST_NAME"));
				reservation.setPhone(rs.getString("PHONE"));
				reservation.setEmail(rs.getString("EMAIL"));
				reservation.setPartySize(rs.getInt("PARTY_SIZE"));
				reservation.setSpecialNeed(rs.getString("SPECIAL_NEED"));

				reservations.add(reservation);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBConnector.closeResources(ps, rs, con);
		}

		return reservations;
	}

	public ReservationVO createReservation(ReservationVO reservation) {

		Connection con = DBConnector.connect();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("INSERT INTO reservations(CONF_NO,RESERVATION_DATE,RESERVATION_TIME,FIRST_NAME,LAST_NAME,PHONE,EMAIL,PARTY_SIZE,SPECIAL_NEED) VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, reservation.getConfNumber());
			ps.setString(2, reservation.getReservationDate());
			ps.setString(3, reservation.getReservationTime());
			ps.setString(4, reservation.getFirstName());
			ps.setString(5, reservation.getLastName());
			ps.setString(6, reservation.getPhone());
			ps.setString(7, reservation.getEmail());
			ps.setInt(8, reservation.getPartySize());
			ps.setString(9, reservation.getSpecialNeed());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				reservation.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DBConnector.closeResources(ps, rs, con);
		}

		return reservation;
	}
}
