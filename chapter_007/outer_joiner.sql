1) select car.id, b.name, e.name, t.name from 
	car left join body b on b.id = car.id_body
		left join engine e on e.id = car.id_engine
		left join transmission t on t.id = car.id_transmission; 
2) select b.name from 
	car right join body b on b.id = car.id_body
	where car.id is null;

select e.name from 
	car right join engine e on e.id = car.id_body
	where car.id is null;

select t.name from 
	car right join transmission t on t.id = car.id_body
	where car.id is null;
	