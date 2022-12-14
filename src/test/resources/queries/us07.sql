select b.name
from books b
         join book_borrow bb
              on b.id = bb.book_id
         join users u
              on bb.user_id = u.id
where b.name = 'The First Men in the Moon';


