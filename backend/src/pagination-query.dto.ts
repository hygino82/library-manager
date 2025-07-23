import { Type } from 'class-transformer';
import { IsOptional, IsPositive } from 'class-validator';

export class PaginationQueryDto {
  @IsOptional()
  @IsPositive()
  @Type(() => Number)
  limit?: number;

  @IsOptional()
  @IsPositive()
  @Type(() => Number)
  offset?: number;
}

export class PageQueryDto {
  @Type(() => Number)
  @IsPositive()
  page: number = 1;

  @Type(() => Number)
  @IsPositive()
  perPage: number = 10;
}

// src/common/dto/paginated-response.dto.ts

export class PaginatedResponseDto<T> {
  data: T[];
  total: number;
  currentPage: number;
  perPage: number;
  totalPages: number;
}
