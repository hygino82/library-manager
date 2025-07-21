import { Injectable } from '@nestjs/common';
import { CreateAuthorDto } from './dto/create-author.dto';
import { UpdateAuthorDto } from './dto/update-author.dto';
import { Author } from './entities/author.entity';
import { ResponseAutorDto } from './dto/response-autor.dto';

@Injectable()
export class AuthorService {

  private authorList: Author[] = [
    {
      id: 1,
      name: 'Machado de Assis',
      country: 'Brasil',
      updatedAt: new Date(2025, 7, 21, 16, 7),
      createdAt: new Date(2025, 7, 21, 16, 7)
    },
    {
      id: 2,
      name: 'Jorge Amado',
      country: 'Brasil',
      updatedAt: new Date(2025, 7, 20, 14, 30),
      createdAt: new Date(2025, 7, 20, 14, 30)
    },
    {
      id: 3,
      name: 'Gabriel García Márquez',
      country: 'Colômbia',
      updatedAt: new Date(2025, 7, 19, 10, 45),
      createdAt: new Date(2025, 7, 19, 10, 45)
    },
    {
      id: 4,
      name: 'Clarice Lispector',
      country: 'Brasil',
      updatedAt: new Date(2025, 7, 22, 12, 15),
      createdAt: new Date(2025, 7, 22, 12, 15)
    },
    {
      id: 5,
      name: 'Pablo Neruda',
      country: 'Chile',
      updatedAt: new Date(2025, 7, 18, 18, 50),
      createdAt: new Date(2025, 7, 18, 18, 50)
    },
    {
      id: 6,
      name: 'Cecília Meireles',
      country: 'Brasil',
      updatedAt: new Date(2025, 7, 21, 9, 20),
      createdAt: new Date(2025, 7, 21, 9, 20)
    }
  ];

  create(createAuthorDto: CreateAuthorDto) {
    const newAuthor: Author = {
      id: this.authorList.length + 1,
      ...createAuthorDto,
      createdAt: new Date(),
      updatedAt: new Date()
    };

    this.authorList.push(newAuthor);
    // Convert to ResponseAutorDto
    return  newAuthor as ResponseAutorDto;
  }

  findAll(): ResponseAutorDto[] {
    return this.authorList.map(a => { return a });
  }

  findOne(id: number): ResponseAutorDto | undefined {
    return this.authorList.find(a => a.id === id);
  }

  update(id: number, updateAuthorDto: UpdateAuthorDto) {
    const authorIndex = this.authorList.findIndex(a => a.id === id);

    if (authorIndex !== -1) {
     return this.authorList[authorIndex] = {
        ...this.authorList[authorIndex],
        ...updateAuthorDto,
        updatedAt: new Date()
      };
    }
    
    return undefined;
  }

  remove(id: number) {
    this.authorList = this.authorList.filter(a => a.id != id);
  }
}
