--Banco de dados
create database rebeldes;



--Tabela para os rebeldes
create table rebelde (id SERIAL primary key,
					  nome varchar(50)not null,
					  idade integer not null,
					  genero varchar(10),
					  localizacao varchar(50) not null,
					  traidor boolean default false,
					  reportar integer default 0);	
					 
--Tabela para os recursos					  
					  
create table recursos ( id serial primary key,
						nome varchar(20)not null,
						value integer not null);					  
						
-- Tabela para o inventário dos rebeldes
create  table inventario (
  rebelde_id integer references rebelde(id),
  recursos_id integer references recursos(id),
  quantidade integer not null,
  primary key (rebelde_id , recursos_id));	
 
-- Inserindo recursos 
 insert into recursos (nome, value) values ('Água', 100), 
 										   ('Comida', 150),
 										   ('Arma', 200),
 										   ('Munição', 1000);
-- Inserindo rebeldes
 insert into rebelde (nome, idade, genero, localizacao) values ('Luke Skywalker', 19, 'Masculino', 'Tatooine'), 										  
 										                       ('Leia Organa', 18, 'Feminino', 'Alderaan'),
 										                       ('Han Solo', 29, 'Masculino', 'Corellia'),
 										                       ('Chewbacca', 35, 'Besta', 'Kashyyyk'),
 										                       ('Obi-Wan Kenobi', 50, 'Humano', 'Stewjon'),
 										                       ('Darth Vader', 30, 'Humano', 'Tatooine');
		

-- Inserindo itens no inventário
 insert into inventario (rebelde_id, recursos_id, quantidade) values (1, 3, 2),
 																	 (2, 2,10),
 										                             (3, 4,100),
 										                             (4, 1,25),
 										                             (5, 4, 100),
 										                             (6, 3, 1);
 										                            
 										                            
-- Tabela para compras
 create  table transacao (id SERIAL primary key,			
 						comprar_id integer references rebelde(id),
 						vender_id integer references rebelde (id),
 						recursos_id integer references recursos(id),
 						quantidade integer not null,
 						total integer not null); 
 					
-- select comprar_item(1,3,1,1);					
 
-- Validar compras 
create or replace function  comprar_item(comprador integer, vendedor integer, item integer, valor integer) returns void as $$
declare
  saldo_comprador integer;
  saldo_vendedor integer;
  preco_item integer;
  estoque_item integer;
  compras_total integer;
begin
	
  -- Verificar se o comprador existe
  select into saldo_comprador reportar
  from rebelde
  where id = comprador;

  -- Verificar se o vendedor existe
  select into saldo_vendedor reportar
  from rebelde
  where id = vendedor;

  -- Verificar se o item existe e obter seu preço e estoque
  select into preco_item, estoque_item value, quantidade
  from recursos
  inner join inventario on inventario.recursos_id = recursos.id
  where inventario.rebelde_id = vendedor
    and inventario.recursos_id = item;

  -- Calcular o total da compra
  compras_total := preco_item * valor;

  -- Verificar se o comprador possui saldo suficiente
  if saldo_comprador >= compras_total then 
  
    -- Verificar se o vendedor possui estoque suficiente
    if estoque_item >= valor then
    
      -- Atualizar o saldo do comprador
      update rebelde
      set reportar = reportar - compras_total
      where id = comprador;

      -- Atualizar o saldo do vendedor
      update rebelde
      set reportar = reportar + compras_total
      where id = vendedor;

      -- Atualizar o estoque do item
      update inventario
      set quantidade = quantidade - valor
      where rebelde_id = vendedor
        and recursos_id = item;

      -- Inserir registro da transação
      insert into transacao (comprar_id, vender_id, recursos_id, quantidade, total)
      VALUES (comprador, vendedor, item, valor, compras_total);
      
    else 
      raise exception 'O vendedor não possui estoque suficiente desse item.';
    end if;
    
  else    
    raise exception 'O comprador não possui saldo suficiente para realizar a compra.';
 
  end if;
  
end;
$$ language plpgsql;

-- INNER JOIN: retorna apenas os rebeldes que fizeram ou receberam alguma compra
select rebelde.id , transacao.id from  rebelde  INNER JOIN transacao ON rebelde.id  = transacao.comprar_id  OR rebelde.id = transacao.vender_id;

-- LEFT JOIN: retorna todos os rebeldes e as compras que eles fizeram ou receberam, se houver
SELECT rebelde.id , transacao.id from rebelde LEFT JOIN transacao ON rebelde.id  = transacao.comprar_id OR rebelde.id  = transacao.vender_id;

-- RIGHT JOIN: retorna todos as compras e os rebeldes que as fizeram ou receberam, se houver
SELECT rebelde.id , transacao.id  from rebelde RIGHT JOIN transacao ON rebelde.id = transacao.comprar_id OR rebelde.id = transacao.vender_id;

-- FULL JOIN: retorna todos os rebeldes e todas as compras, relacionando-os quando possível
-- SELECT rebelde.id , transacao.id  from rebelde FULL JOIN transacao ON rebelde.id = transacao.comprar_id OR rebelde.id = transacao.vender_id;


-- Reportar Vader como traidor pela primeira vez
-- update rebelde set reportar = reportar + 1 where id = 4;

-- Se Vader tiver três ou mais reportes, marcar como traidor e inativo
-- update rebelde set traidor = true where id = 4 AND reportar >=3;

-- Calcular a porcentagem de traidores
 select ROUND(COUNT(case when traidor  = true then id end) * 100.0 / COUNT(*),2) as traidor_porcentagem from rebelde;

-- Calcular a porcentagem de rebeldes
 select ROUND(COUNT(case when traidor  = false then id end) * 100.0 / COUNT(*),2) as rebelde_percentagem from rebelde;

--insert into rebelde (nome, idade, genero, localizacao) values ('Well', 41, 'Javas', 'Catalisa');

--select * from rebelde;