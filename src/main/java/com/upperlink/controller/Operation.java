package com.upperlink.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.upperlink.enums.Skill;
import com.upperlink.model.Developer;
import com.upperlink.model.Registration;
import com.upperlink.model.Skills;
import com.upperlink.pojo.Summary;

public class Operation {

	private static SessionFactory sessionFactory; 
	private static Logger logger = Logger.getLogger(Operation.class);
	
	public Operation() {
		createConnection();
	}
	
	/**
	 * Persist Developer information and skill into database
	 * @param firstName
	 * @param lastName
	 * @param skill
	 * @return
	 */
	public void registerDeveloper(Developer developer , Skills skill){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			//persist mapping			
			Registration registration = new Registration( developer, skill);
			session.save(registration);

			transaction.commit();
			
		}catch(Exception ex){
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
	}
	
	/**
	 * Add a Developer
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public Long addDeveloper(String firstName, String lastName){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Long developerID = null;
		try{
			transaction = session.beginTransaction();
			//persist the developer info
			Developer developer = new Developer(firstName, lastName);
			developerID = (Long)session.save(developer);
			transaction.commit();
			return developerID;
			
		}catch(Exception ex){
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * Get Developer from database
	 * @param developerID
	 * @return {@link Developer}
	 */
	public Developer getDeveloper(Long developerID){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("FROM Developer d WHERE d.id = :developerID");
			query.setParameter("developerID", developerID);
			Developer developer = (Developer)query.uniqueResult();
			transaction.commit();
			
			return developer;
			
		} catch (Exception ex) {
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * Populate the database with skills 
	 */
	public void populateSkills() {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			int i = 0;
			for (Skill skill : Skill.values()) {
				session.save(new Skills(skill));
			    if ( i % 5 == 0 ) {
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
			}
			transaction.commit();
			
		}catch(Exception ex){
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
	}
	
	public void addSkill(Skill skill) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(new Skills(skill));
			transaction.commit();
			
		}catch(Exception ex){
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
	}
	
	/**
	 * Get Skill from database
	 * @param skill
	 * @return {@link Skills}
	 */
	public Skills getSkill(Skill skill){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("FROM Skills s WHERE s.skill = :developerSkill");
			query.setParameter("developerSkill", skill);
			Skills developerSkill = (Skills)query.uniqueResult();
			transaction.commit();
			
			return developerSkill;

		} catch (Exception ex) {
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * Get all Skills from database
	 * @param skill
	 * @return {@link Skills}
	 */
	public List<Skills> getAllSkill(){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		List<Skills> allSkills = new ArrayList<>();
		
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("FROM Skills s");
			List result = query.list();
	        for (Iterator iterator = result.iterator(); iterator.hasNext();){
	        	allSkills.add((Skills) iterator.next()); 
	        }
	        
			transaction.commit();
		} catch (Exception ex) {
			if(transaction != null){
				transaction.rollback();
			}
			logger.error("Exception : ",ex);
		}finally {
			session.close();
		}
		return allSkills;
	}
	
	/**
	 * Summary of the registration
	 * @return
	 */
	public List<Summary> getSummary(){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Summary> summaryList = new ArrayList<>();
		
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("SELECT COUNT(r.developerId), r.skill.skill FROM Registration r GROUP BY r.skill.skill");
			List result = query.list();
	        for (Iterator iterator = result.iterator(); iterator.hasNext();){
	        	
	        	Object[] stats = (Object[])iterator.next();
	        	summaryList.add(new Summary(Integer.parseInt(stats[0].toString()), stats[1].toString())); 
	        }			
			
			transaction.commit();
			System.out.println(summaryList);

			return summaryList;
			
			}catch(Exception ex){
				if(transaction != null){
					transaction.rollback();
				}
				logger.error("Exception : ",ex);
			}finally{
				session.close();
			}
		return null;
	}
	
	public Skill getEnum(String skill){
		switch (skill) {
		case "CSharp" :
			return Skill.CSharp;
		case "Java" :
			return Skill.Java;
		case "PHP" :
			return Skill.PHP;
		case "C" :
			return Skill.C;
		case "Objective C" :
			return Skill.Objective_C;	
		default:
			return null;
		}
	}
	
	
	private void createConnection(){
	     try{
	    	 sessionFactory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
}
