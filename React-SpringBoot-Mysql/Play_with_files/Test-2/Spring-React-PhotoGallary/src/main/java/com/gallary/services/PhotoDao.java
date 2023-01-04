package com.gallary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gallary.model.Photos;

import com.gallary.repo.PhotoRepo;

@Service
public class PhotoDao {

	@Autowired
	private PhotoRepo repo;

	public Photos savePhoto(Photos photo) {

		Photos save = repo.save(photo);
		return save;
	}

	public List<Photos> getAll() {
		List<Photos> list = repo.findByOrderByIdDesc();
		return list;
	}

}
