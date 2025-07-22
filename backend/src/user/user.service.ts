import { Injectable } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { Repository } from 'typeorm';
import { User } from './entities/user.entity';
import { InjectRepository } from '@nestjs/typeorm';
import * as bcrypt from 'bcrypt';
import { ResponseUserDto } from './dto/response-user.dto';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User)
    private readonly userRepo: Repository<User>,
  ) {}

  async create(data: CreateUserDto) {
    const saltOrRounds = 10;
    const hashedPassword = await bcrypt.hash(data.password, saltOrRounds);

    const user = this.userRepo.create({
      ...data,
      password: hashedPassword,
      bookList: [],
    });

    const savedUser = await this.userRepo.save(user);

    return new ResponseUserDto(savedUser);
  }

  async findAll() {
    const users = await this.userRepo.find({ relations: ['bookList'] });
    return users.map((user) => new ResponseUserDto(user));
  }

  findOne(id: number) {
    return `This action returns a #${id} user`;
  }

  update(id: number, updateUserDto: UpdateUserDto) {
    return `This action updates a #${id} user`;
  }

  remove(id: number) {
    return `This action removes a #${id} user`;
  }
}
