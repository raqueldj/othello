package io.avengers.othello.jpa;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EmWorker {

	public Object work(EntityManager em);
}
