import { Book } from '../../book/entities/book.entity';

import {
  Column,
  CreateDateColumn,
  Entity,
  OneToMany,
  PrimaryGeneratedColumn,
  UpdateDateColumn,
} from 'typeorm';

export enum UserType {
  ALUNO = 'ALUNO',
  FUNCIONARIO = 'FUNCIONARIO',
  ADMIN = 'ADMIN',
}

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  firstName: string;

  @Column()
  lastName: string;

  @Column({ unique: true })
  email: string;

  @Column()
  password: string;

  @Column()
  phone: string;

  @Column({
    type: 'enum',
    enum: UserType,
    default: UserType.ALUNO,
  })
  userType: UserType;

  @OneToMany(() => Book, (book) => book.user, { cascade: true })
  bookList: Book[];

  @CreateDateColumn()
  createdAt: Date;

  @UpdateDateColumn()
  updatedAt: Date;
}
