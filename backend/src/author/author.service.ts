import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateAuthorDto } from './dto/create-author.dto';
import { Author } from './entities/author.entity';
import { ResponseAuthorDto } from './dto/response-autor.dto';
import { UpdateAuthorDto } from './dto/update-author.dto';
import { PageQueryDto, PaginatedResponseDto } from '../pagination-query.dto';

@Injectable()
export class AuthorService {
  constructor(
    @InjectRepository(Author)
    private readonly authorRepository: Repository<Author>,
  ) {}

  async createAuthor(
    insertAuthor: CreateAuthorDto,
  ): Promise<ResponseAuthorDto> {
    const author = new Author();
    author.name = insertAuthor.name;
    author.country = insertAuthor.country;
    const res = await this.authorRepository.save(author);
    return new ResponseAuthorDto(res);
  }

  async findAllPaged({
    page,
    perPage,
  }: PageQueryDto): Promise<PaginatedResponseDto<ResponseAuthorDto>> {
    const skip = (page - 1) * perPage;
    const take = perPage;

    const [result, total] = await this.authorRepository.findAndCount({
      skip,
      take,
    });

    const authors = result.map((author) => new ResponseAuthorDto(author));

    return {
      data: authors,
      total,
      currentPage: page,
      perPage,
      totalPages: Math.ceil(total / perPage),
    };
  }

  async getAuthors(): Promise<ResponseAuthorDto[]> {
    const authors = await this.authorRepository.find();
    return authors.map((author) => new ResponseAuthorDto(author));
  }

  async findOne(id: number): Promise<ResponseAuthorDto | null> {
    const author = await this.authorRepository.findOneBy({ id });
    return author ? new ResponseAuthorDto(author) : null;
  }

  async update(
    id: number,
    updateAuthorDto: UpdateAuthorDto,
  ): Promise<ResponseAuthorDto | null> {
    const author = await this.authorRepository.findOneBy({ id });

    if (!author) {
      return null;
    }

    author.name = updateAuthorDto.name;
    author.country = updateAuthorDto.country;
    author.updatedAt = new Date();
    const updatedAuthor = await this.authorRepository.save(author);

    return new ResponseAuthorDto(updatedAuthor);
  }

  async remove(id: number): Promise<void> {
    await this.authorRepository.delete(id);
  }
}
