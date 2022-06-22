CREATE TABLE "person" (
                               "id" serial NOT NULL,
                               "name" text NOT NULL,
                               "login" text NOT NULL,
                               "password" text NOT NULL,
                               "e_mail" varchar(30) NOT NULL,
                               "access_level" integer NOT NULL,
                               "funds" double precision NOT NULL,
                               "blocked_status" integer NOT NULL,
                               "role_id" integer NOT NULL,
                               CONSTRAINT "user_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
    );



CREATE TABLE "offers" (
                                 "id" serial NOT NULL,
                                 "name" text NOT NULL,
                                 "description" text NOT NULL,
                                 "price" double precision NOT NULL,
                                 "category_id" integer NOT NULL,
                                 CONSTRAINT "offers_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
    );



CREATE TABLE "role" (
                               "id" integer NOT NULL,
                               "name" text NOT NULL,
                               CONSTRAINT "role_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
    );



CREATE TABLE "plan" (
                               "id" integer NOT NULL,
                               "user_id" integer NOT NULL,
                               "status_id" integer NOT NULL,
                               CONSTRAINT "plan_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
    );



CREATE TABLE "plan_status" (
                                      "id" integer NOT NULL,
                                      "name" text NOT NULL,
                                      CONSTRAINT "plan_status_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
    );



CREATE TABLE "category" (
                                   "id" integer NOT NULL,
                                   "name" text NOT NULL,
                                   "description" text NOT NULL,
                                   CONSTRAINT "category_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
    );



CREATE TABLE "offer_content" (
                                        "plan_id" integer NOT NULL,
                                        "offer_id" integer NOT NULL
) WITH (
      OIDS=FALSE
    );



ALTER TABLE "person" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("role_id") REFERENCES "role"("id");

ALTER TABLE "offers" ADD CONSTRAINT "offers_fk0" FOREIGN KEY ("category_id") REFERENCES "category"("id");


ALTER TABLE "plan" ADD CONSTRAINT "plan_fk0" FOREIGN KEY ("user_id") REFERENCES "person"("id");
ALTER TABLE "plan" ADD CONSTRAINT "plan_fk1" FOREIGN KEY ("status_id") REFERENCES "plan_status"("id");



ALTER TABLE "offer_content" ADD CONSTRAINT "offer_content_fk0" FOREIGN KEY ("plan_id") REFERENCES "plan"("id");
ALTER TABLE "offer_content" ADD CONSTRAINT "offer_content_fk1" FOREIGN KEY ("offer_id") REFERENCES "offers"("id");







