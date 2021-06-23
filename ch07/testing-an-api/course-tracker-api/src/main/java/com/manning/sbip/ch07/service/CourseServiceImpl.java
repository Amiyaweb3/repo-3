package com.manning.sbip.ch07.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Optional<Course> getCourseById(long courseId) {
		return courseRepository.findById(courseId);
	}

	@Override
	public Iterable<Course> getCoursesByCategory(String category) {
		return courseRepository.findAllByCategory(category);
	}

	@Override
	public Iterable<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course updateCourse(Long courseId, Course course) {

		Optional<Course> optionalCourse = courseRepository.findById(courseId);
		if(optionalCourse.isPresent()) {
			Course dbCourse = optionalCourse.get();
			dbCourse.setName(course.getName());
			dbCourse.setCategory(course.getCategory());
			dbCourse.setDescription(course.getDescription());
			dbCourse.setRating(course.getRating());
			
			return courseRepository.save(dbCourse);
		}
		return null;
	}

	@Override
	public void deleteCourses() {
		courseRepository.deleteAll();
	}

	@Override
	public void deleteCourseById(long courseId) {
		courseRepository.deleteById(courseId);
	}

}
