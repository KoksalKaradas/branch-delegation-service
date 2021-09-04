DROP TABLE IF EXISTS BRANCH_DELEGATION;

create table BRANCH_DELEGATION (
    app_id bigint not null,
    state varchar(255),
    primary key (app_id)
);

INSERT INTO BRANCH_DELEGATION (app_id, state) VALUES
  (1, 'FIRST_USER'),
  (2, 'MANAGER'),
  (3, 'WAITING'),
  (4, 'REJECTED'),
  (5, 'COMPLETED')