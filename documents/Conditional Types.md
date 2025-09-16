在 TypeScript 中，**条件类型**（Conditional Types）是一种基于条件逻辑动态确定类型的工具类型。条件类型允许开发者根据类型的某些特性（如类型关系或约束）来选择不同的类型，类似于 JavaScript 中的三元运算符。条件类型是 TypeScript 类型系统中非常强大的特性，广泛用于类型推断、类型选择和高级类型操作。

### 条件类型的定义
条件类型的语法如下：

```typescript
T extends U ? X : Y
```

- `T`：待检查的类型。
- `U`：条件类型检查的约束。
- `X`：如果 `T` 满足 `U` 的约束，则使用类型 `X`。
- `Y`：如果 `T` 不满足 `U` 的约束，则使用类型 `Y`。

条件类型通常在泛型中使用，通过类型约束和推断动态生成类型。

### 条件类型的基本形式
一个简单的条件类型示例：

```typescript
type IsString<T> = T extends string ? true : false;

type A = IsString<string>; // true
type B = IsString<number>; // false
```

在这个例子中，`IsString<T>` 检查类型 `T` 是否是 `string` 的子类型。如果是，则返回 `true`；否则返回 `false`。

### 条件类型的具体作用
条件类型在 TypeScript 编程中有以下几个主要作用：

1. **类型选择**：
   条件类型可以根据输入类型的特性选择不同的类型。例如，基于类型的不同返回不同类型：

   ```typescript
   type TypeName<T> = T extends string
     ? "string"
     : T extends number
     ? "number"
     : T extends boolean
     ? "boolean"
     : "object";

   type T1 = TypeName<string>; // "string"
   type T2 = TypeName<number>; // "number"
   type T3 = TypeName<boolean>; // "boolean"
   type T4 = TypeName<object>; // "object"
   ```

2. **类型过滤**：
   条件类型可以用来过滤不符合条件的类型，常与映射类型结合使用。例如，提取对象中特定类型的属性：

   ```typescript
   type FilterByType<T, U> = {
     [K in keyof T as T[K] extends U ? K : never]: T[K];
   };

   interface User {
     name: string;
     age: number;
     email: string;
     active: boolean;
   }

   type StringFields = FilterByType<User, string>;
   // 等价于：
   // type StringFields = {
   //   name: string;
   //   email: string;
   // }
   ```

   这里通过条件类型和映射类型的 `as` 关键字过滤出值为 `string` 类型的属性。

3. **类型推断与 `infer`**：
   条件类型支持 `infer` 关键字，用于从类型中推断出子类型，常用于复杂类型操作。例如，提取函数返回值的类型：

   ```typescript
   type ReturnType<T> = T extends (...args: any) => infer R ? R : never;

   type Func = (x: number) => string;
   type Return = ReturnType<Func>; // string
   ```

   这里 `infer R` 推断出函数的返回值类型 `R`，并在条件成立时返回该类型。

4. **类型转换**：
   条件类型可以用来转换类型。例如，将联合类型中的特定类型替换为其他类型：

   ```typescript
   type ReplaceType<T, From, To> = T extends From ? To : T;

   type Replaced = ReplaceType<string | number | boolean, string, null>;
   // 等价于：null | number | boolean
   ```

5. **与联合类型结合**：
   条件类型在处理联合类型时会“分发”（Distributive Conditional Types）。当 `T` 是联合类型时，条件类型会对联合类型的每个成员分别应用。例如：

   ```typescript
   type ToArray<T> = T extends any ? T[] : never;

   type Result = ToArray<string | number>;
   // 等价于：string[] | number[]
   ```

   这种分发特性使得条件类型非常适合处理联合类型的复杂场景。

6. **内置工具类型中的应用**：
   TypeScript 的许多内置工具类型都基于条件类型实现，例如：
   - `Exclude<T, U>`：从 `T` 中排除可以赋值给 `U` 的类型。
   - `Extract<T, U>`：从 `T` 中提取可以赋值给 `U` 的类型。
   - `NonNullable<T>`：从 `T` 中排除 `null` 和 `undefined`。
   - `ReturnType<T>`：提取函数类型的返回值类型。
   - `Parameters<T>`：提取函数类型的参数类型。

   示例：
   ```typescript
   type T = Exclude<string | number | null, null>; // string | number
   type U = Extract<string | number | boolean, string | boolean>; // string | boolean
   type V = NonNullable<string | null | undefined>; // string
   ```

### 实际应用场景
1. **类型安全的函数重载**：
   条件类型可以用来根据参数类型动态确定返回值类型：

   ```typescript
   type ProcessInput<T> = T extends string ? string : T extends number ? number : null;

   function process<T>(input: T): ProcessInput<T> {
     if (typeof input === "string") {
       return input.toUpperCase() as ProcessInput<T>;
     } else if (typeof input === "number") {
       return (input * 2) as ProcessInput<T>;
     }
     return null as ProcessInput<T>;
   }

   const str = process("hello"); // string
   const num = process(42); // number
   const obj = process({}); // null
   ```

2. **类型安全的工具函数**：
   条件类型可以用来编写类型安全的工具函数。例如，提取嵌套对象的值类型：

   ```typescript
   type DeepValue<T> = T extends object ? DeepValue<T[keyof T]> : T;

   interface Nested {
     a: { b: { c: string } };
     d: number;
   }

   type Value = DeepValue<Nested>; // string | number
   ```

3. **处理 API 数据**：
   条件类型可以用来处理 API 响应的类型，确保类型安全。例如，检查 API 返回值的类型并进行转换：

   ```typescript
   type ApiResponse<T> = T extends { error: string } ? { status: "error"; message: string } : { status: "success"; data: T };

   const success: ApiResponse<{ id: number }> = { status: "success", data: { id: 1 } };
   const error: ApiResponse<{ error: string }> = { status: "error", message: "Failed" };
   ```

4. **类型约束与校验**：
   条件类型可以用来校验输入类型是否符合要求。例如，确保某个类型是特定类型的子类型：

   ```typescript
   type AssertString<T> = T extends string ? T : never;

   function logString<T>(value: AssertString<T>): void {
     console.log(value);
   }

   logString("hello"); // 合法
   // logString(42); // 错误：number 不能赋值给 never
   ```

5. **高级类型操作**：
   条件类型在库开发中非常常见。例如，TypeScript 的标准库使用条件类型实现 `Awaited<T>` 来提取 `Promise` 的值类型：

   ```typescript
   type Awaited<T> = T extends Promise<infer U> ? Awaited<U> : T;

   type Result = Awaited<Promise<Promise<string>>>; // string
   ```

### 注意事项
1. **分发特性（Distributive Conditional Types）**：
   条件类型在处理联合类型时会自动分发，这可能导致意外的结果。如果不想分发，可以用方括号包裹泛型参数：

   ```typescript
   type NonDistributive<T> = [T] extends [string] ? true : false;

   type T1 = NonDistributive<string | number>; // false
   type T2 = string | number extends string ? true : false; // false（不分发）
   type T3 = string | number extends any ? true : false; // true（分发）
   ```

2. **类型推断的复杂性**：
   使用 `infer` 进行复杂类型推断可能导致类型系统难以解析，需谨慎设计以避免性能问题或类型错误。

3. **与映射类型的结合**：
   条件类型常与映射类型结合使用，特别是在键的重新映射（`as` 关键字）中。例如：

   ```typescript
   type PickByType<T, U> = {
     [K in keyof T as T[K] extends U ? K : never]: T[K];
   };
   ```

4. **限制条件类型的嵌套**：
   过多的条件类型嵌套可能导致类型推断变慢或类型错误，建议在复杂场景下拆分逻辑或使用中间类型。

5. **与 `never` 的交互**：
   如果条件类型的结果是 `never`，表示类型不匹配，常用在类型排除或约束中：

   ```typescript
   type NonString<T> = T extends string ? never : T;

   type Result = NonString<string | number | boolean>; // number | boolean
   ```

### 总结
条件类型是 TypeScript 中用于动态选择和转换类型的核心特性，通过 `extends` 和 `infer` 提供强大的类型推断能力。它们在实际编程中的作用包括类型选择、过滤、推断、转换以及实现类型安全的工具函数和 API 处理。条件类型与联合类型、映射类型和内置工具类型（如 `Exclude`、`ReturnType`）结合使用，可以处理复杂的类型场景。开发者需要注意分发特性、类型推断的复杂性以及性能问题，以充分发挥条件类型在类型安全和代码复用中的优势。