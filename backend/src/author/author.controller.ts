import { Controller, Get, Post, Body, Put, Param, Delete, Query } from '@nestjs/common';
import { AuthorService } from './author.service';
import { CreateAuthorDto } from './dto/create-author.dto';
import { UpdateAuthorDto } from './dto/update-author.dto';
import { PageQueryDto } from '../pagination-query.dto';

@Controller('author')
export class AuthorController {
  constructor(private readonly authorService: AuthorService) { }

  @Post()
  create(@Body() createAuthorDto: CreateAuthorDto) {
    return this.authorService.createAuthor(createAuthorDto);
  }

  @Get()
  findAll(@Query() query: PageQueryDto) {
    return this.authorService.findAllPaged(query);
  }


  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.authorService.findOne(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() updateAuthorDto: UpdateAuthorDto) {
    const author = this.authorService.update(+id, updateAuthorDto);
    if (author) {
      author;
      return author;
    } else {
      return { message: 'Author not found' };
    }
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.authorService.remove(+id);
  }
}

