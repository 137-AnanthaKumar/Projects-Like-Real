package com.example.irctc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.irctc.model.Ticket;
@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {

	Ticket findByPnr(Long pnr);

}
